-- create table 문

-- 사용자 테이블 생성
CREATE TABLE Consumers ( 
    consumer_id VARCHAR(5) PRIMARY KEY,
    consumer_name VARCHAR(255) NOT NULL,
    consumer_pw VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    location_state CHAR(20) NOT NULL,
    location_city CHAR(20) NOT NULL
);

-- 식당 테이블 생성
CREATE TABLE Restaurants (
    restaurant_id VARCHAR(5) PRIMARY KEY,
    restaurant_name VARCHAR(255) NOT NULL,
    owner_name VARCHAR(255) NOT NULL,
    owner_pw VARCHAR(255) NOT NULL,
    res_location_state char(20) NOT NULL,
    res_location_city char(20) NOT NULL,
    description VARCHAR(255) NOT NULL
);

-- 메뉴 테이블 생성
CREATE TABLE Menus (
    menu_id VARCHAR(5) PRIMARY KEY,
    restaurant_id VARCHAR(5) NOT NULL,
    menu_name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    CONSTRAINT price_check CHECK (price > 0),
    description VARCHAR(255) NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES Restaurants(restaurant_id)
);

-- 예약 테이블 생성
CREATE TABLE Reservations (
    reservation_id VARCHAR(5) PRIMARY KEY,
    consumer_id VARCHAR(5) NOT NULL,
    restaurant_id VARCHAR(5) NOT NULL,
    reservation_date DATE NOT NULL,
    reservation_time TIME NOT NULL,
    number_of_people INT NOT NULL,
    CONSTRAINT check_people_num CHECK (number_of_people > 0),
    status ENUM('confirmed', 'cancelled', 'out of due') DEFAULT 'confirmed',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (consumer_id) REFERENCES Consumers(consumer_id),
    FOREIGN KEY (restaurant_id) REFERENCES Restaurants(restaurant_id)
);

-- insert 문

INSERT INTO Restaurants (restaurant_id, restaurant_name, owner_name, owner_pw, res_location_state, res_location_city, description) VALUES
('R001', '맛있는 한식', '김현수', 'password123', '서울', '강남구', '전통 한식과 현대적인 요리가 어우러진 곳'),
('R002', '이탈리아 음식점', '이태리', 'italy456', '서울', '종로구', '이탈리안 피자와 파스타 전문점'),
('R003', '더 버거샵', '박버거', 'burger789', '부산', '해운대구', '신선한 재료로 만든 수제 버거'),
('R004', '초밥왕', '최초밥', 'sushi101', '부산', '남포동', '신선한 해산물로 만드는 최고의 초밥');

INSERT INTO Menus (menu_id, restaurant_id, menu_name, price, description) VALUES
('M001', 'R001', '김치찌개', 8000.00, '맛있는 김치찌개, 김치와 돼지고기가 듬뿍!'),
('M002', 'R001', '된장찌개', 7500.00, '시골식 된장찌개'),
('M003', 'R002', '피자', 15000.00, '매콤한 페퍼로니 피자'),
('M004', 'R002', '스파게티', 12000.00, '토마토 소스의 클래식 스파게티');

INSERT INTO Consumers (consumer_id, consumer_name, consumer_pw, email, location_state, location_city) VALUES
('C001', '김철수', 'chulsu123', 'chulsu@example.com', '서울', '강남구'),
('C002', '이영희', 'younghi456', 'younghi@example.com', '부산', '해운대구'),
('C003', '박지민', 'jimin789', 'jimin@example.com', '대구', '수성구'),
('C004', '최유리', 'yuri101', 'yuri@example.com', '인천', '남동구');

-- 식당 테이블

-- select 문
			SELECT * FROM restaurants;
			SELECT * FROM restaurants where (restaurant_name = ?);
			SELECT * FROM restaurants where (description = ?);
			SELECT * FROM restaurants where (owner_name = ?);
			SELECT * FROM restaurants where (res_location_state = ?);
			SELECT * FROM restaurants where (res_location_city = ?);
			SELECT * FROM restaurants where (restaurant_name = ?) AND (owner_pw = ?);
            
-- delete 문
			DELETE FROM restaurants WHERE (restaurant_name = ?) AND (owner_pw = ?);
            
-- insert 문
			INSERT INTO restaurants VALUES ( ? , ? , ? , ? , ? , ? , ? );


-- 고객 테이블

-- select 문
			SELECT COUNT(*) FROM Consumers;
			SELECT COUNT(*) FROM Consumers WHERE (consumer_name=?);
			SELECT * FROM Consumers;
			SELECT * FROM Consumers WHERE (consumer_name=?) and (consumer_pw=?);
			SELECT * FROM Consumers where (consumer_id = ?);
			SELECT * FROM Consumers where (consumer_name = ?);
            
-- delete 문
			DELETE FROM Consumers WHERE (consumer_id - ?);
            
-- insert 문
			INSERT INTO Consumers(consumer_id, consumer_name, consumer_pw, email, location_state, location_city) 
			VALUES(?,?,?,?,?,?) ON DUPLICATE KEY UPDATE 
			consumer_name = VALUES(consumer_name), consumer_pw = VALUES(consumer_pw), email = VALUES(email),
			location_state = VALUES(location_state), location_city = VALUES(location_city);


-- 메뉴 테이블

-- select 문
			select * from menus;
			select * from menus m inner join restaurants r on m.restaurant_id=r.restaurant_id where (r.restaurant_name = ?);
			SELECT * FROM Menus WHERE restaurant_id = ? AND menu_name = ?;
            SELECT * FROM Menus WHERE restaurant_id = ?;
	        SELECT * FROM Menus WHERE menu_name LIKE ?;
            SELECT MAX(CAST(SUBSTRING(menu_id, 2, LENGTH(menu_id)-1) AS UNSIGNED)) FROM Menus;
-- delete 문
			DELETE FROM Menus WHERE menu_id = ? AND restaurant_id = ?;
-- insert 문
			INSERT INTO Menus(menu_id, restaurant_id, menu_name, price, description) VALUES(?,?,?,?,?);


-- 예약 테이블

-- select 문
			SELECT * FROM Reservations;
			SELECT * FROM reservations a inner join consumers b on a.consumer_id =b.consumer_id where (b.consumer_name = ?);
			SELECT * FROM reservations a inner join restaurants b on a.restaurant_id =b.restaurant_id where (b.restaurant_name = ?);
			SELECT * FROM Reservations where (consumer_id = ?) and (restaurant_id = ?)and (reservation_date = ?);
			SELECT * FROM Reservations where (consumer_id = ?) and (reservation_date = ?) and (reservation_time = ?);
			SELECT COUNT(*) FROM Reservations;
-- delete 문
			DELETE FROM Reservations WHERE CONCAT(reservation_date, ' ', reservation_time) < NOW();
			DELETE FROM Reservations where (consumer_id = ?) and (restaurant_id = ?) and (reservation_date = ?);
-- insert 문
			INSERT INTO Reservations VALUES (?, ?, ?, ?, ?, ?, 'confirmed', NOW());