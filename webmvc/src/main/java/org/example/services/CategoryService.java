package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.dtos.category.CategoryItemDTO;
import org.example.entities.CategoryEntity;
import org.example.mappers.CategoryMapper;
import org.example.repositories.ICategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final ICategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final ImageService imageService;

    public List<CategoryItemDTO> getAll() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }

    public CategoryItemDTO getById(Long id) {
        var category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        return categoryMapper.toDto(category);
    }

    public void save(CategoryItemDTO dto) {

        CategoryEntity entity = categoryMapper.toEntity(dto);

        if (dto.getImage() != null && dto.getImage().startsWith("http")) {
            String fileName = imageService.downloadFromUrl(dto.getImage());
            entity.setImage(fileName);
        }

        categoryRepository.save(entity);
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}