package vn.edu.hcmuaf.fit.efootwearspringboot.constants;

public class QUERY {

    public static class VERIFY {
        public static final String FIND_TOKEN = "select * from verifies where token=? and expire_time > now() and is_verified=false";
    }

    public static class ADDRESS_DELIVERY {
        public static final String FIND_ADDRESSES = "select * from address_delivery where account_id= ? and state= 'ACTIVE'";
        public static final String FIND_BY_ID_AND_ACCOUNT_ID = "select * from address_delivery where id=? and account_id= ?";
    }

    public static class ACCOUNT {

        public static final String FIND_ACCOUNT = "select * from accounts where email = ? or username = ?";
    }

    public static class CUSTOMER {
        public static final String FIND_BY_ACCOUNT = "SELECT c.* from customers c JOIN accounts a on a.customer_id = c.id where a.id = ?";
    }

    public static class CATEGORY {
        public static final String FIND_ALL = "select * from categories where state = 'ACTIVE'";
        public static final String FIND_CATEGORY = "select * from categories where state = 'ACTIVE' and id = ? ";
        public static final String FIND_CATEGORY_BY_SLUG = "select * from categories where state = 'ACTIVE' and slug = ? ";

        public static final String FIND_PARENT_CATEGORY = "select * from categories where state = 'ACTIVE' and parent_id is null";
        public static final String FIND_CHILDREN_CATEGORY = "select * from categories where state = 'ACTIVE' and parent_id = ?";
    }

    public static class COLOR {
        public static final String FIND_ALL = "select * from colors where state = 'ACTIVE'";
        public static final String FIND_COLOR = "select * from colors where state = 'ACTIVE' and id = ? ";
    }

    public static class SIZE {
        public static final String FIND_ALL = "select * from sizes where state = 'ACTIVE'";
        public static final String FIND_SIZE = "select * from sizes where state = 'ACTIVE' and id = ? ";
    }

    public static class PRODUCT {
        public static final String FIND_PRODUCTS = "select * from products where state = 'ACTIVE'";
        public static final String FIND_PRODUCT_BY_ID = "select * from products p " +
                "where p.state = 'ACTIVE' and p.id = ? ";
        public static final String FIND_PRODUCT_BY_SLUG = "select * from products where state = 'ACTIVE' and slug = ? ";
        public static final String FIND_PRODUCT_BY_SLUG_COLOR = "select * from products where state = 'ACTIVE' and slug = ? and color_id = ? ";

        public static final String FIND_PRODUCTS_HOT = "select * from products where state = 'ACTIVE' order by create_at desc limit 15";
        public static final String FIND_PRODUCTS_NEWS = " select p.* from products p  JOIN product_details d on d.product_id = p.id  where p.state = 'ACTIVE'  order by d.stock_quantity DESC limit 15";

        public static final String FIND_PRODUCT_BY_CATE_SLUG = "select p.* from products p join categories c on c.id = p.category_id where p.state = 'ACTIVE' and c.slug = ? ";

        public static final String COUNT_PRODUCT_BY_SLUG = "SELECT COUNT(*) from products where slug = ?";
    }

    public static class DETAIL {
        public static final String FIND_DETAIL_BY_PRODUCT = "select d.* from product_details d join products p on d.product_id = p.id where p.state = 'ACTIVE' and d.size_id=? and p.slug=? and p.color_id=?";
        public static final String FIND_DETAILS_BY_PRODUCT = "select d.* from product_details d join products p on d.product_id = p.id where p.state = 'ACTIVE' and p.slug=? and p.color_id=?";
        public static final String FIND_ALL = "select * from product_details ";
    }

    public static class GALLERY {

        public static final String FIND_GALLERIES_BY_TYPE = "select g.* from galleries g join type_galleries t on g.type_gallery_id = t.id where t.type_code = ?";
        public static final String FIND_CAROUSELS = "select g.* from galleries g join type_galleries t on g.type_gallery_id = t.id where t.type_code = 'slide'";
        public static final String FIND_BANNERS = "select g.* from galleries g join type_galleries t on g.type_gallery_id = t.id where t.type_code = 'banner'";
        public static final String FIND_COLLECTIONS = "select g.* from galleries g join type_galleries t on g.type_gallery_id = t.id where t.type_code = 'collection'";
        public static final String FIND_ADS = "select g.* from galleries g join type_galleries t on g.type_gallery_id = t.id where t.type_code = 'ads'";
        public static final String FIND_FOOTER = "select g.* from galleries g join type_galleries t on g.type_gallery_id = t.id where t.type_code = 'footer'";
        public static final String FIND_ALL = "select * from galleries where state = 'ACTIVE'";
    }

    public static class GALLERY_TYPE {
        public static final String FIND_ALL = "select * from type_galleries where state = 'ACTIVE'";
        public static final String FIND_GALLERY_TYPE = "select * from type_galleries where state = 'ACTIVE' and id = ? ";
        public static final String FIND_GALLERY_BY_TYPE = "select * from type_galleries where state = 'ACTIVE' and type_code = ? ";
    }

    public static class COUPON {
        public static final String FIND_ALL = "select * from coupons where end_time > now() && max_usage> 0 ";
        public static final String FIND_COUPON = "select * from coupons where end_time > now() && max_usage> 0 and id = ? ";
        public static final String FIND_COUPON_BY_CODE = "select * from coupons where end_time > now() && max_usage> 0 and code = ? ";
    }
}
