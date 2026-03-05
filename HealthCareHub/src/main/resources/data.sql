INSERT INTO hospitals (id, name, address)
VALUES (1, 'Apollo Hospital', 'Banglore'),
       (2, 'Fortis Hospital', 'Chennai'),
       (3, 'Manipal Hospital', 'Hyderabad');


INSERT INTO medicines
(medicine_name, manufacturer, description, dosage, price, stock_quantity, is_active, hospital_id, created_time, updated_time)
VALUES
    ('Paracetamol 500mg', 'Cipla', 'Used for fever and pain relief', '500mg', 25.00, 100, 'ACTIVE', 1, NOW(), NOW()),
    ('Augmentin 625', 'GSK',    'Antibiotic for bacterial infections', '625mg', 95.00, 70, 'ACTIVE', 2, NOW(), NOW()),
    ('Pantoprazole 40mg', 'Sun Pharma', 'Acidity and gastric treatment', '40mg', 22.00, 110, 'ACTIVE', 2, NOW(), NOW());