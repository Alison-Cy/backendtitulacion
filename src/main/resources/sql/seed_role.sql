INSERT INTO roles (name, description, status, created_at, updated_at)
VALUES
    ('Administrador', 'Acceso completo al sistema', 'Activo', NOW(), NOW()),
    ('Coordinador', 'Gestión de estudiantes de sus carreras', 'Activo', NOW(), NOW()),
    ('Tutor', 'Evaluación de estudiantes asignados', 'Activo', NOW(), NOW()),
    ('Estudiante', 'Acceso a sus asignaturas y documentos', 'Activo', NOW(), NOW());

-- INSERT INTO permissions (name, description) VALUES
--                                                 ('read:posts','Leer posts'),
--                                                 ('create:posts','Crear posts'),
--                                                 ('delete:posts','Borrar posts') ON CONFLICT (name) DO NOTHING;

-- Asignar permisos a roles (sin tocar admin)
-- INSERT INTO role_permissions (role_id, permission_id)
-- SELECT r.id, p.id FROM roles r, permissions p
-- WHERE (r.name='ROLE_EDITOR' AND p.name IN ('read:posts','create:posts'))
--    OR (r.name='ROLE_VIEWER' AND p.name='read:posts')
--     ON CONFLICT DO NOTHING;
