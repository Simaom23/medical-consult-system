-- Insert specialties
INSERT INTO specialty (name, created_at, updated_at) VALUES 
    ('Dermatology', NOW(), NOW()),
    ('Ophthalmology', NOW(), NOW()),
    ('Radiology', NOW(), NOW()),
    ('Family Medicine', NOW(), NOW()),
    ('Pediatrics', NOW(), NOW());

-- Insert doctors
INSERT INTO doctor (name, created_at, updated_at) VALUES 
    ('António', NOW(), NOW()),
    ('Maria', NOW(), NOW()),
    ('Carlos', NOW(), NOW()),
    ('Gabriela', NOW(), NOW()),
    ('Paulo', NOW(), NOW());

-- Insert patients
INSERT INTO patient  (name, age, created_at, updated_at) VALUES 
    ('Manuel', 53, NOW(), NOW()),
    ('Joana', 32, NOW(), NOW()),
    ('Ana', 25, NOW(), NOW()),
    ('Diogo', 33, NOW(), NOW()),
    ('Catarina', 33, NOW(), NOW()),
    ('Miguel', 40, NOW(), NOW());

-- Insert pathologies
INSERT INTO pathology (name, created_at, updated_at) VALUES 
    ('Pathology 1', NOW(), NOW()),
    ('Pathology 2', NOW(), NOW()),
    ('Pathology 3', NOW(), NOW()),
    ('Pathology 4', NOW(), NOW()),
    ('Pathology 5', NOW(), NOW()),
    ('Pathology 6', NOW(), NOW()),
    ('Pathology 7', NOW(), NOW());

-- Insert symptoms
INSERT INTO symptom (description, created_at, updated_at) VALUES 
    ('Symptom 1 Description', NOW(), NOW()),
    ('Symptom 2 Description', NOW(), NOW()),
    ('Symptom 3 Description', NOW(), NOW()),
    ('Symptom 4 Description', NOW(), NOW()),
    ('Symptom 5 Description', NOW(), NOW()),
    ('Symptom 6 Description', NOW(), NOW()),
    ('Symptom 7 Description', NOW(), NOW()),
    ('Symptom 8 Description', NOW(), NOW()),
    ('Symptom 9 Description', NOW(), NOW()),
    ('Symptom 10 Description', NOW(), NOW()),
    ('Symptom 11 Description', NOW(), NOW()),
    ('Symptom 12 Description', NOW(), NOW()),
    ('Symptom 13 Description', NOW(), NOW()),
    ('Symptom 14 Description', NOW(), NOW()),
    ('Symptom 15 Description', NOW(), NOW());

-- Insert consults
INSERT INTO consult (doctor_id, specialty_id, patient_id, created_at, updated_at) VALUES 
    (1, 1, 1, NOW(), NOW()), -- António, Dermatology, Manuel (53), Pathology 1
    (1, 1, 1, NOW(), NOW()), -- António, Dermatology, Manuel (53), Pathology 2
    (2, 2, 1, NOW(), NOW()), -- Maria, Ophthalmology, Manuel (53), Pathology 3
    (2, 2, 2, NOW(), NOW()), -- Maria, Ophthalmology, Joana (32), Pathology 3
    (3, 3, 3, NOW(), NOW()), -- Carlos, Radiology, Ana (25), Pathology 4
    (4, 4, 4, NOW(), NOW()), -- Gabriela, Family Medicine, Diogo (33), Pathology 5
    (5, 5, 5, NOW(), NOW()), -- Paulo, Pediatrics, Catarina (33), Pathology 6
    (2, 2, 6, NOW(), NOW()); -- Maria, Ophthalmology, Miguel (40), Pathology 7


-- Pathology and Symptom Relationships
INSERT INTO pathology_symptom (pathology_id, symptom_id) VALUES 
    (1, 1), (1, 2),  -- Pathology 1 with Symptoms 1 and 2
    (2, 3), (2, 4), (2, 5),  -- Pathology 2 with Symptoms 3, 4, 5
    (3, 6), (3, 7),  -- Pathology 3 with Symptoms 6, 7
    (4, 8), (4, 9),  -- Pathology 4 with Symptoms 8, 9
    (5, 10), (5, 11),  -- Pathology 5 with Symptoms 10, 11
    (6, 12), (6, 13),  -- Pathology 6 with Symptoms 12, 13
    (7, 14), (7, 15);  -- Pathology 7 with Symptoms 14, 15

-- Linking patients to their pathologies
INSERT INTO patient_pathology (patient_id, pathology_id) VALUES
    (1, 1), -- Manuel (53) has Pathology 1
    (1, 2), -- Manuel (53) has Pathology 2
    (1, 3), -- Manuel (53) has Pathology 3
    (2, 3), -- Joana (32) has Pathology 3
    (3, 4), -- Ana (25) has Pathology 4
    (4, 5), -- Diogo (33) has Pathology 5
    (5, 6), -- Catarina (33) has Pathology 6
    (6, 7); -- Miguel (40) has Pathology 7


-- Linking consults to symptoms
INSERT INTO consult_symptom (consult_id, symptom_id) VALUES
    (1, 1), -- Consult 1 has Symptom 1
    (1, 2), -- Consult 1 has Symptom 2
    (2, 3), -- Consult 2 has Symptom 3
    (2, 4), -- Consult 2 has Symptom 4
    (2, 5), -- Consult 2 has Symptom 5
    (3, 6), -- Consult 3 has Symptom 6
    (4, 6), -- Consult 4 has Symptom 6
    (4, 7), -- Consult 4 has Symptom 7
    (5, 8), -- Consult 5 has Symptom 8
    (5, 9), -- Consult 5 has Symptom 9
    (6, 10), -- Consult 6 has Symptom 10
    (6, 11), -- Consult 6 has Symptom 11
    (7, 12), -- Consult 7 has Symptom 12
    (7, 13), -- Consult 7 has Symptom 13
    (8, 14), -- Consult 8 has Symptom 14
    (8, 15); -- Consult 8 has Symptom 15
