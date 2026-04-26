package org.example.seeders;

import com.github.slugify.Slugify;
import net.datafaker.Faker;
import org.example.entities.CategoryEntity;
import org.example.repositories.ICategoryRepository;
import org.example.services.ImageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class CategorySeeder implements CommandLineRunner {

    private final ImageService imageService;
    private final ICategoryRepository categoryRepository;

    private final Slugify slugify = Slugify.builder().transliterator(true).build();
    private final Faker faker = new Faker(new Locale("uk"));

    public CategorySeeder(ICategoryRepository categoryRepository,
                          ImageService imageService) {
        this.categoryRepository = categoryRepository;
        this.imageService = imageService;
    }

    @Override
    public void run(String... args) {

        if (categoryRepository.count() > 0) {
            return;
        }

        for (int i = 0; i < 10; i++) {

            CategoryEntity category = new CategoryEntity();

            String name = faker.commerce().department();
            category.setName(name);

            category.setSlug(slugify.slugify(name));

            String imageUrl = "https://picsum.photos/600/400?random=" + System.nanoTime();

            try {
                String fileName = imageService.downloadFromUrl(imageUrl);
                category.setImage(fileName);
            } catch (Exception e) {
                category.setImage("default.jpg");
            }

            categoryRepository.save(category);
        }
    }
}