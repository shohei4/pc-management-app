INSERT INTO software (id, name, created_at) VALUES (1, 'Office', CURRENT_TIMESTAMP) ;
INSERT INTO software (id, name, created_at) VALUES (2, 'Illustrator/Photoshop', CURRENT_TIMESTAMP);

ALTER TABLE pc_software
  ADD CONSTRAINT fk_pc_software_pc
  FOREIGN KEY (pc_id) REFERENCES pc(id) ON DELETE CASCADE;

ALTER TABLE pc_software
  ADD CONSTRAINT fk_pc_software_software
  FOREIGN KEY (software_id) REFERENCES software(id);
  