package vn.edu.hcmuaf.fit.efootwearspringboot.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.category.CategoryDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.category.CategoryWithChildrenDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Category;

import java.util.List;

@Mapper(componentModel = "spring")
@Component("categoryMapper")
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(target = "children", source = "category.childrenCategory")
    CategoryWithChildrenDto toChildrenDto(Category category);

    List<CategoryWithChildrenDto> toChildrenDtos(List<Category> categories);

    default CategoryDto toDto(Category category) {
        if (category == null) {
            return null;
        }
        CategoryDto.CategoryDtoBuilder<?, ?> categoryDto = CategoryDto.builder();
        categoryDto.category(toDto(category.getParentCategory()));
        categoryDto.gallery(GalleryMapper.INSTANCE.toSlimDto(category.getGallery()));
        categoryDto.id(category.getId());
        categoryDto.name(category.getName());
        categoryDto.slug(category.getSlug());
        categoryDto.state(category.getState());
        categoryDto.createAt(category.getCreateAt());
        categoryDto.updateAt(category.getUpdateAt());

        return categoryDto.build();
    }

    default Category toEntity(CategoryDto categoryDto) {
        if (categoryDto == null) {
            return null;
        }
        Category.CategoryBuilder<?, ?> category = Category.builder();

        category.parentCategory(toEntity(categoryDto.getCategory()));
        category.id(categoryDto.getId());
        category.name(categoryDto.getName());
        category.slug(categoryDto.getSlug());
        category.state(categoryDto.getState());
        category.createAt(categoryDto.getCreateAt());
        category.updateAt(categoryDto.getUpdateAt());

        return category.build();
    }

    List<Category> toEntities(List<CategoryDto> categoryDtos);

    List<CategoryDto> toDtos(List<Category> categories);
}
