DELETE FROM TRANSACTIONS;

DELETE FROM WALLETS;

INSERT INTO WALLETS (ID, CPF, EMAIL, "PASSWORD", "TYPE", BALANCE)
VALUES
    (1, '263.323.500-45', 'mirian@test.com', '123456', 2, 1000.00),
    (2, '423.323.434-44', 'hamed@test.com', '123456', 2, 1000.00),
    (3, '233.323.353-56', 'mateus@test.com', '123456', 1, 1000.00),
    (4, '893.453.353-70', 'iago@test.com', '123456', 1, 1000.00);
