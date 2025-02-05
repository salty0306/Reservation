USE catchtableDB;

-- 사용자 테이블 생성
CREATE TABLE Consumers ( 
    consumer_id VARCHAR(5) PRIMARY KEY,
    consumer_name VARCHAR(255) NOT NULL,
    consumer_pw VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    location_state CHAR(20) NOT NULL,
    location_city CHAR(20) NOT NULL
);

CREATE TABLE Restaurants (
    restaurant_id VARCHAR(5) PRIMARY KEY,
    restaurant_name VARCHAR(255) NOT NULL,
    owner_name VARCHAR(255) NOT NULL,
    owner_pw VARCHAR(255) NOT NULL,
    res_location_state char(20) NOT NULL,
    res_location_city char(20) NOT NULL,
    description TEXT
);

-- 메뉴 테이블 생성
CREATE TABLE Menus (
    menu_id VARCHAR(5) PRIMARY KEY,
    restaurant_id VARCHAR(5) NOT NULL,
    menu_name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    CONSTRAINT price_check CHECK (price > 0),
    description TEXT,
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

-- 예시: 예약 취소 기능 구현
-- 예약 상태를 업데이트하려면 UPDATE 문을 사용
-- 예: UPDATE Reservations SET status = 'cancelled' WHERE reservation_id = 1
