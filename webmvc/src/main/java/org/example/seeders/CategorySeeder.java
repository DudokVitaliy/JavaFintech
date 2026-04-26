package org.example.seeders;

import com.github.slugify.Slugify;
import net.datafaker.Faker;
import org.example.entities.CategoryEntity;
import org.example.repositories.ICategoryRepository;
import org.example.services.FileService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Random;

@Component
public class CategorySeeder implements CommandLineRunner {

    private final ICategoryRepository categoryRepository;
    private final FileService fileService;
    private final Slugify slugify = Slugify.builder().transliterator(true).build();

    private final Faker faker = new Faker(new Locale("uk"));
    private final Random random = new Random();

    public CategorySeeder(ICategoryRepository categoryRepository, FileService fileService) {
        this.categoryRepository = categoryRepository;
        this.fileService = fileService;
    }

    @Override
    public void run(String... args) {

        if (categoryRepository.count() > 0) {
            return;
        }

        for (int i = 0; i < 10; i++) {

            CategoryEntity category = new CategoryEntity();

            // рандомна назва
            String name = faker.commerce().department();
            category.setName(name);

            // slug
            category.setSlug(slugify.slugify(name));

            String imageUrl = "https://picsum.photos/600/400?random=" + System.nanoTime();

            try {
                String fileName = fileService.load(imageUrl);
                category.setImage(fileName);
            } catch (Exception e) {
                category.setImage("default.jpg");
            }

            categoryRepository.save(category);
        }
    }
}