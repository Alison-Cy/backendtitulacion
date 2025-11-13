INSERT INTO roles (name, description) VALUES ('ROLE_ADMIN','Admin con todos los permisos'), ('ROLE_EDITOR','Editor'), ('ROLE_VIEWER','Viewer') ON CONFLICT (name) DO NOTHING;

INSERT INTO permissions (name, description) VALUES
                                                ('read:posts','Leer posts'),
                                                ('create:posts','Crear posts'),
                                                ('delete:posts','Borrar posts') ON CONFLICT (name) DO NOTHING;

-- Asignar permisos a roles (sin tocar admin)
INSERT INTO role_permissions (role_id, permission_id)
SELECT r.id, p.id FROM roles r, permissions p
WHERE (r.name='ROLE_EDITOR' AND p.name IN ('read:posts','create:posts'))
   OR (r.name='ROLE_VIEWER' AND p.name='read:posts')
    ON CONFLICT DO NOTHING;
