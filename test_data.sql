USE catchtableDB;

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
