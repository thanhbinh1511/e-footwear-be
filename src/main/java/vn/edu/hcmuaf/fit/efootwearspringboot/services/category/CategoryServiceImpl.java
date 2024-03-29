package vn.edu.hcmuaf.fit.efootwearspringboot.services.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import vn.edu.hcmuaf.fit.efootwearspringboot.dto.category.CategoryDto;
import vn.edu.hcmuaf.fit.efootwearspringboot.exception.InternalServerException;
import vn.edu.hcmuaf.fit.efootwearspringboot.exception.NotFoundException;
import vn.edu.hcmuaf.fit.efootwearspringboot.mapper.CategoryMapper;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Category;
import vn.edu.hcmuaf.fit.efootwearspringboot.repositories.CategoryRepository;
import vn.edu.hcmuaf.fit.efootwearspringboot.constants.EntityState;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.MyParser;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.BaseResult;
import vn.edu.hcmuaf.fit.efootwearspringboot.utils.result.DataResult;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public DataResult findCategory(Long id) {
        Optional<Category> optional = categoryRepository.findCategoryById(id);
        if (optional.isPresent()) {
            return DataResult.success(categoryMapper.toDto(optional.get()));
        }
        throw new NotFoundException("Không tìm thấy danh mục");
    }

    @Override
    public DataResult findCategoryBySlug(String slug) {
        Optional<Category> optional = categoryRepository.findCategoryBySlug(slug);
        if (optional.isPresent()) {
            return DataResult.success(categoryMapper.toDto(optional.get()));
        }
        throw new NotFoundException("Không tìm thấy danh mục");
    }

    @Override
    public DataResult findCategories() {
        Optional<List<Category>> optional = categoryRepository.findCategories();
        if (optional.isPresent()) {
            return DataResult.success(categoryMapper.toDtos(optional.get()));
        }
        throw new NotFoundException("không tìm thấy category!");
    }

    @Override
    public BaseResult deleteCategory(Long id) {
        Optional<Category> optional = categoryRepository.findCategoryById(id);
        if (optional.isPresent()) {
            Category category = optional.get();
            category.setState(EntityState.DELETED);
            if (!ObjectUtils.isEmpty(categoryRepository.save(category))) {
                return BaseResult.success();
            }
            throw new InternalServerException("Không thể xoá được danh mục.");
        }
        throw new NotFoundException("Không tìm thấy danh mục");
    }

    @Override
    public BaseResult createCategory(CategoryDto categoryDto) {
        // convert name to slug
        String slug = MyParser.convertToSlug(categoryDto.getName());

        // set slug = slug + parent's slug
        if (categoryDto.getCategory() != null) {
            Optional<Category> optionalParent = categoryRepository.findCategoryById(categoryDto.getCategory().getId());
            if (optionalParent.isPresent()) {
                slug = MyParser.convertToSlug(categoryDto.getName()) + "-" + optionalParent.get().getSlug();
            }
        }

        // tìm kiếm category với slug
        Optional<Category> optionalCategory = categoryRepository.findCategoryBySlug(slug);
        if (optionalCategory.isPresent())
            return BaseResult.error(HttpStatus.BAD_REQUEST, "Category đã tồn tại");

        categoryDto.setState(EntityState.ACTIVE);
        categoryDto.setSlug(slug);
        Category category = categoryMapper.toEntity(categoryDto);

        if (!ObjectUtils.isEmpty(categoryRepository.save(category))) {
            return BaseResult.success();
        }
        throw new InternalServerException("Không thể tạo được danh mục.");
    }

    @Override
    public BaseResult updateCategory(CategoryDto categoryDto) {
        String slug = MyParser.convertToSlug(categoryDto.getName());
        if (categoryDto.getCategory() != null) {
            Optional<Category> optionalParent = categoryRepository.findCategoryById(categoryDto.getCategory().getId());
            if (optionalParent.isPresent()) {
                slug = MyParser.convertToSlug(categoryDto.getName()) + "-" + optionalParent.get().getSlug();
            }
        }

        Optional<Category> optional = categoryRepository.findCategoryById(categoryDto.getId());
        if (optional.isPresent()) {
            Category category = optional.get();
            category.setName(categoryDto.getName());
            category.setSlug(slug);
            category.setParentCategory(categoryMapper.toEntity(categoryDto.getCategory()));

            if (!ObjectUtils.isEmpty(categoryRepository.save(category))) {
                return BaseResult.success();
            }
            throw new InternalServerException("Không thể cập nhật được danh mục.");
        }
        throw new NotFoundException("Không tìm thấy danh mục");
    }

    @Override
    public DataResult findParentCategory() {
        Optional<List<Category>> optional = categoryRepository.findParentCategory();
        if (optional.isPresent()) {
            return DataResult.success(categoryMapper.toChildrenDtos(optional.get()));
        }
        throw new NotFoundException("Không tìm thấy category parent");
    }
}
