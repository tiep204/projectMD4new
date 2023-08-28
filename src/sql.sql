create database projectMD4new;
use projectMD4new;

create table Catalog
(
    catalog_id   int primary key auto_increment,
    catalog_name nvarchar(255),
    status       bit default (1)
);

create table product
(
    product_id   int primary key auto_increment,
    product_name nvarchar(255),
    catalog_id   int,
    description  text,
    price        float,
    image        text,
    stock        int,
    status       bit      default (1),
    created_at   datetime default (now()),
    foreign key (catalog_id) references Catalog (catalog_id)
);



create table Image
(
    image_id   int primary key auto_increment,
    imageURL   text,
    product_id int,
    foreign key (product_id) references Product (product_id)
);

create table user
(
    user_id     int primary key auto_increment,
    username    nvarchar(255),
    email       nvarchar(255),
    first_name  nvarchar(255),
    last_name   nvarchar(255),
    password    nvarchar(255),
    phoneNumber nvarchar(255),
    address     nvarchar(255),
    gender      bit,
    birthDate   date,
    role        int default (0),
    status      bit default (1),
    avatar      text,
    created_at  datetime
);

ALTER TABLE user
    ADD CONSTRAINT uc_username UNIQUE (username);



create table orders
(
    order_id    int primary key auto_increment,
    user_id     int,
    order_at    datetime,
    total_price int,
    status      int,
    note        nvarchar(255),
    created_at  datetime,
    foreign key (user_id) references user (user_id)
);

create table orderDetail
(
    order_detail_id int primary key auto_increment,
    order_id        int,
    product_id      int,
    quantity        int,
    foreign key (order_id) references orders (order_id),
    foreign key (product_id) references product (product_id)
);

create table contact
(
    id         int primary key,
    full_name  nvarchar(255),
    email      nvarchar(255),
    content    nvarchar(255),
    status     int,
    created_at datetime
);

create table wishlist
(
    wishlist_id int primary key auto_increment,
    user_id     int,
    product_id  int,
    created_at  datetime,
    foreign key (user_id) references user (user_id),
    foreign key (product_id) references product (product_id)
);

delimiter //
create procedure proc_getAll()
begin
    select catalog_id, catalog_name, status from Catalog;
end //
delimiter ;

delimiter //
create procedure proc_insertCategory(in pro_catName nvarchar(255))
begin
    insert into Catalog(catalog_name) values (pro_catName);
end //
delimiter ;

delimiter //
create procedure proc_findById(in pro_id int)
begin
    select catalog_id, catalog_name, status from catalog where catalog_id = pro_id;
end //
delimiter ;


delimiter //
create procedure proc_updateCategory(in pro_id int, in pro_catName nvarchar(255), in pro_status bit)
begin
    update Catalog
    set catalog_name = pro_catName,
        status       = pro_status
    where catalog_id = pro_id;
end //
delimiter ;

DELIMITER //

CREATE PROCEDURE proc_changeStatusCategory(IN categoryId INT)
BEGIN
    UPDATE catalog
    SET status = NOT status
    WHERE catalog_id = categoryId;
END //

DELIMITER ;

delimiter //
create procedure proc_findByCatalogName(in pro_catName nvarchar(255))
begin
    select catalog_id, catalog_name, status from Catalog where catalog_name like concat('%', pro_catName, '%');
end //
delimiter ;


delimiter //
create procedure proc_updateProduct(in pro_id int, in pro_name nvarchar(255), in pro_catId int, in pro_description text,
                                    in pro_price float, in pro_image text, in pro_stock int, in pro_status bit)
begin
    update product
    set product_name = pro_name,
        catalog_id   = pro_catId,
        description  = pro_description,
        price        = pro_price,
        image        = pro_image,
        stock        = pro_stock,
        status       = pro_status
    where product_id = pro_id;
end //
delimiter ;


delimiter //
create procedure proc_deleteimage(IN imId int)
begin
    delete from image where product_id = imId;
end //
delimiter ;


delimiter //
create procedure proc_changeStatusProductById(in pro_id int)
begin
    UPDATE product
    SET status = NOT status
    WHERE product_id = pro_id;
end //
delimiter ;


delimiter //
create procedure proc_findByProductName(in pro_name nvarchar(255))
begin
    select product_id,
           product_name,
           catalog_id,
           description,
           price,
           image,
           stock,
           status,
           created_at
    from product
    where product.product_name like concat('%', pro_name, '%');
end //
delimiter ;

delimiter //
create procedure proc_findProductById(in pro_id int)
begin
    select product_id,
           product_name,
           catalog_id,
           description,
           price,
           image,
           stock,
           status,
           created_at
    from product
    where product_id = pro_id;
end //
delimiter ;

delimiter //
create procedure proc_findAllProduct()
begin
    select product_id,
           product_name,
           catalog_id,
           description,
           price,
           image,
           stock,
           status,
           created_at
    from product;
end //;

delimiter //
create procedure proc_insertImage(in pro_url text, pro_id int)
begin
    insert into image(imageURL, product_id) values (pro_url, pro_id);
end //
delimiter ;


delimiter //
create procedure proc_insertProduct(in pro_name nvarchar(255), in pro_catId int, in pro_description text,
                                    in pro_price float, in pro_image text, in pro_stock int, OUT proIdOut int)
begin
    insert into product(product_name, catalog_id, description, price, image, stock)
    values (pro_name, pro_catId, pro_description, pro_price, pro_image, pro_stock);
    select LAST_INSERT_ID() into proIdOut;
end //
delimiter ;

delimiter //
create procedure proc_getimageById(in idIm int)
begin
    select image_id, imageURL, product_id from image where product_id = idIm;
end //
delimiter ;


delimiter //
create procedure proc_blockUser(in uId int)
begin
    update user set status = false where user_id = uId;
end //
delimiter ;


delimiter //
create procedure proc_unLockUser(in uId int)
begin
    update user set status = true where user_id = uId;
end //
delimiter ;

delimiter //
create procedure proc_getAllUser()
begin
    select user_id,
           username,
           email,
           first_name,
           last_name,
           password,
           phoneNumber,
           address,
           gender,
           birthDate,
           role,
           status,
           avatar,
           created_at
    from user;
end //
delimiter ;


delimiter //
create procedure proc_getUserById(in uId int)
begin
    select user_id,
           username,
           email,
           first_name,
           last_name,
           password,
           phoneNumber,
           address,
           gender,
           birthDate,
           role,
           status,
           avatar,
           created_at
    from user
    where user_id = uId;
end //
delimiter ;


delimiter //
create procedure proc_insertUser(in uName nvarchar(255), in uEmail nvarchar(255), in uFirstName nvarchar(255),
                                 in uLastname nvarchar(255), in uPassword nvarchar(255), in uPhoneNumber nvarchar(255),
                                 in uAddress nvarchar(255), in uGender int, in uBirthDate date, in uRole int,
                                 in uAvatar text, in uCreateDate datetime)
begin
    insert into user(username, email, first_name, last_name, password, phoneNumber, address, gender, birthDate, role,
                     avatar, created_at)
    values (uName, uEmail, uFirstName, uLastname, uPassword, uPhoneNumber, uAddress, uGender, uBirthDate, uRole,
            uAvatar, uCreateDate);
end //
delimiter ;

delimiter //
create procedure proc_login(in uName nvarchar(255), in uPassword nvarchar(255))
begin
    select *
    from user
    where username = uName
      and password = uPassword;
end //
delimiter ;

delimiter //
create procedure proc_register(in uName nvarchar(255), in uEmail nvarchar(255), in uFirstName nvarchar(255),
                               in uLastName nvarchar(255), in uPassword nvarchar(255), in uPhoneNumber nvarchar(255),
                               in uAddress nvarchar(255),
                               in uGender bit, in uBirthDate date, in uRole int, in uCreateDate datetime)
begin
    insert into user(username, email, first_name, last_name, password, phoneNumber, address, gender, birthDate, role,
                     created_at)
    values (uName, uEmail, uFirstName, uLastName, uPassword, uPhoneNumber, uAddress, uGender, uBirthDate, uRole,
            uCreateDate);
end //
delimiter ;


delimiter //
create procedure proc_searchUserByName(in uName nvarchar(255))
begin
    select * from user where username like concat('%', uName, '%');
end //
delimiter ;

delimiter //
create procedure proc_updateUser(in uId int, in uName nvarchar(255), in uEmail nvarchar(255),
                                 in uFirstName nvarchar(255),
                                 in uLastName nvarchar(255), in uPassword nvarchar(255), in uPhoneNumber nvarchar(255),
                                 in uAddress nvarchar(255),
                                 in uGender bit, in uBirthDate date)
begin
    update user
    set username    = uName,
        email       = uEmail,
        first_name  = uFirstName,
        last_name   = uLastName,
        password    = uPassword,
        phoneNumber = uPhoneNumber,
        address     = uAddress,
        gender      = uGender,
        birthDate   = uBirthDate
    where user_id = uId;

end //
delimiter ;

delimiter //
create procedure proc_login(in uName nvarchar(255), in uPassword nvarchar(255))
begin
    select *
    from user
    where user.username = uName
      and user.password = uPassword;
end //

DELIMITER //
CREATE PROCEDURE getHotProducts()
BEGIN
    SELECT * FROM Product LIMIT 12;
end //
DELIMITER ;

delimiter //
create procedure proc_searchUsername(in pro_username nvarchar(255))
begin
    select * from user where username = pro_username;
end //;


delimiter //
create procedure proc_searchOrderByUserIdAndStatus(in proUserId int)
begin
    select * from orders where user_id = proUserId and status = 0;
end //
delimiter ;


delimiter //
create procedure proc_findByOrderId(in proOId int)
begin
    select * from orderDetail where order_id = proOId;
end //
delimiter ;


delimiter //
create procedure proc_saveOrderDetail(in proOrderId int, in proProId int, in qTy int)
begin
    insert into orderDetail(order_id, product_id, quantity) values (proOrderId, proProId, qTy);
end //
delimiter ;

delimiter //
create procedure proc_UpdateOrderDetail(in proOrdtId int, in proOrderId int, in proProId int, in qTy int)
begin
    update orderDetail
    set order_id   = proOrderId,
        product_id = proProId,
        quantity   = qTy
    where order_detail_id = proOrdtId;

end //
delimiter ;


delimiter //
create procedure proc_saveOrderss(in pUserId int, in totalPrice int, in pnote nvarchar(255))
begin
    insert into orders(user_id, total_price, note,status) values (pUserId, totalPrice, pnote,0);
end //
delimiter ;


delimiter //
create procedure proc_UpdateOrder(in pOrderId int, in pro_userID int, in pro_totalPrice int, in pro_Status int,
                                  in pro_note nvarchar(255))
begin
    update orders
    set user_id     = pro_userID,
        total_price = pro_totalPrice,
        status      = pro_Status,
        note        = pro_note
    where order_id = pOrderId;

end //
delimiter ;
call proc_searchOrderByUserIdAndStatus(12);


delimiter //
create procedure checkStatusProduct()
begin
    select * from product where status = 1;
end //
delimiter ;