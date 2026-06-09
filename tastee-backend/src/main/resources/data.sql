INSERT INTO users (id, username, password, profile_picture_url, bio) VALUES
  (1, 'devuser', 'devpass', 'https://t4.ftcdn.net/jpg/03/64/21/11/360_F_364211147_1qgLVxv1Tcq0Ohz3FawUfrtONzz8nq3e.jpg', 'Development account used for local testing.'),
  (2, 'ava', 'avapass', 'https://images.example.com/users/ava.png', 'Weeknight meal prep and quick lunch ideas.'),
  (3, 'leo', 'leopass', 'https://images.example.com/users/leo.png', 'Comfort food and seasonal ingredients.'),
  (4, 'nina', 'ninapass', 'https://images.example.com/users/nina.png', 'Plant-forward recipes with bold flavors.');

INSERT INTO follow (follower_id, followed_id) VALUES
  (1, 2),
  (1, 3),
  (2, 1),
  (3, 1),
  (4, 1),
  (4, 2);

INSERT INTO post (id, title, author_id, created_at, likes, img_url, calories, description) VALUES
  (1, 'Dev Breakfast Bowl', 1, TIMESTAMP '2026-06-01 08:15:00', 12, 'https://www.wellplated.com/wp-content/uploads/2025/07/Best-Breakfast-Bowl-Recipe.jpg', 420, 'Greek yogurt, berries, oats, and honey for a quick start.'),
  (2, 'Lunch Wraps', 2, TIMESTAMP '2026-06-02 12:20:00', 8, 'https://www.allrecipes.com/thmb/eqialk5XYhhxh-QbCVuyeWTUXX4=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/58246-make-ahead-lunch-wraps-DDMFS-beauty-4x3-45398122cc6e4607b9f438a1c174f857.jpg', 610, 'Turkey wraps with crunchy vegetables and herb dressing.'),
  (3, 'Pasta Night', 3, TIMESTAMP '2026-06-03 18:05:00', 21, 'https://www.yummytummyaarthi.com/wp-content/uploads/2022/11/red-sauce-pasta-1.jpg', 780, 'Creamy tomato pasta with basil and parmesan.'),
  (4, 'Green Curry', 4, TIMESTAMP '2026-06-04 19:10:00', 17, 'https://hot-thai-kitchen.com/wp-content/uploads/2022/04/Green-curry-chicken-sq-2-500x500.jpg', 540, 'Coconut curry with broccoli, snap peas, and jasmine rice.');

INSERT INTO post_interaction (id, user_id, post_id, type, created_at) VALUES
  (1, 2, 1, 'LIKE', TIMESTAMP '2026-06-05 09:00:00'),
  (2, 3, 1, 'BOOKMARK', TIMESTAMP '2026-06-05 09:05:00'),
  (3, 1, 2, 'LIKE', TIMESTAMP '2026-06-05 09:10:00'),
  (4, 4, 3, 'LIKE', TIMESTAMP '2026-06-05 09:15:00'),
  (5, 1, 4, 'BOOKMARK', TIMESTAMP '2026-06-05 09:20:00');

ALTER TABLE post ALTER COLUMN id RESTART WITH 100;
ALTER TABLE post_interaction ALTER COLUMN id RESTART WITH 100;