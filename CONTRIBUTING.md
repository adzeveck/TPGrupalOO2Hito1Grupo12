# Cómo trabajar en este repo

## Setup inicial (una sola vez, cada integrante)

1. Cloná el repo e importalo en Eclipse (`File → Import → Existing Projects into Workspace`).
2. Definí la classpath variable que usa el proyecto para encontrar los `.jar` de Hibernate,
   **sin que cada uno tenga que pisar la ruta del otro**:
   - `Window → Preferences → Java → Build Path → Classpath Variables`
   - `New...` → Name: `HIBERNATE_LIBS` → Path: la carpeta donde tengas los `.jar` de Hibernate 5
     que bajaste de la cátedra (juntá todos en una sola carpeta, sin subcarpetas).
   - `Apply and Close`. El proyecto debería compilar solo; si no, click derecho en el
     proyecto → `Refresh`, y después `Project → Clean...`.
3. Copiá `src/hibernate.cfg.xml.example` como `src/hibernate.cfg.xml` (sin el `.example`),
   y completá `connection.username` / `connection.password` con las credenciales de
   **tu** MySQL local. Ese archivo está en `.gitignore`: nunca se sube al repo, cada uno
   tiene el suyo.
   - Usen todos el mismo nombre de base: `epicentro_gourmet` (ya viene así en el template,
     y con `createDatabaseIfNotExist=true` para que se cree sola la primera vez que corran
     el proyecto).
4. Corré `src/test/TestConexion.java` como Java Application. Si imprime `Conexión OK`,
   tu setup está listo.

## Git workflow

- `main` siempre tiene que compilar y andar — nadie pushea directo ahí.
- Una rama por caso de uso: `feature/<tu-nombre>-<breve-descripcion>`
  (ej: `feature/juan-consulta-cocineros-antiguedad`).
- Commits chicos y descriptivos.
- Pull Request hacia `main` con **al menos 1 aprobación** de otro integrante antes de mergear.
- Merge por *squash*, así el historial de `main` queda un commit por caso de uso.
- Antes de arrancar a programar: `git pull origin main`.
- Cada vez que suban un Caso de Uso, agreguen la fila correspondiente en la tabla de
  "Registro de actualizaciones" del [README](README.md) (lo pide la letra del TP).

## Estructura del proyecto

```
src/
  datos/     → clases del dominio (Festival, UnidadDeVenta, Empleado, etc. — según el diagrama de clases)
  mapeos/    → los .hbm.xml de Hibernate para cada clase de datos/
  dao/       → acceso a datos (HibernateUtil ya está armado; sumen un Dao por caso de uso)
  negocio/   → capa de negocio, si hace falta lógica antes de llegar al dao
  test/      → mains para probar cada Caso de Uso (TestConexion.java ya prueba la conexión)
```
