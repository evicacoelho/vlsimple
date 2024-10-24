### vlsimple monorepo

This is an public project to create a simple webapp which is able to handle audio and video files which are compatible with web browsers and run them locally (at first, in the future run from other websites) close but much simpler than the well known VideoLAN Client (VLC).

This project aims for the user to be able to run most kinds of audio and media files without the need of a thir part software, with an web app (that aims in  the future to be portable, saveable and usable offline).

---

## Tech Stack

### Backend:
- **Java 23**
- **Spring Boot 3.3.4**
- **Hibernate 6.6.1**
- **Liquibase**
- **PostgreSQL** (in Docker)
- **Apache Tika** for metadata extraction

### Frontend:
- **React** with **TypeScript**
- **Yarn/NPM**
- **Webpack** and **Babel**

---

## Setup Instructions

### Backend

1. **Clone the repository**:
   ```bash
   git clone git@github.com:evicacoelho/vlsimple.git
   cd vlsimple/
   ```

2. **Configure PostgreSQL**:
   - The project is configured to use PostgreSQL. You can update the database URL, username, and password in the `application.properties` file.

3. **Run Liquibase**:
   - Ensure you have Liquibase installed, then run the following command to apply database migrations:
     ```bash
     mvn liquibase:update
     ```

4. **Run the backend**:
   - Start the Spring Boot server:
     ```bash
     mvn clean install -DskipTests
     mvn spring-boot:run
     ```

   The backend should now be available at `http://localhost:8080/api`.

### Frontend

1. **Navigate to the frontend folder**:
   ```bash
   cd ../frontend-vlsimple
   ```

2. **Install dependencies**:
   ```bash
   yarn install
   ```

3. **Run the frontend**:
   - Start the development server:
     ```bash
     yarn start
     ```

   The frontend should now be available at `http://localhost:3000`.

---

## Endpoints

### Media Endpoints

- `GET /api/media` — Retrieve all media.
- `POST /api/media` — Add new media manually.
- `POST /api/media/file` — Add a media file using its path.
- `POST /api/media/folder` — Add media by parsing all files in a folder.
- `GET /api/media/{id}` — Retrieve media by ID.

---

## Usage

1. **Upload Media (WIP)**:
   - Navigate to the **Media** page at `http://localhost:3000/media`.
   - Click the "Upload Media" button to open the modal.
   - You can choose either a file or a folder to upload media files. If a folder is selected, all supported files within the folder will be added to the media library.

2. **View Media**:
   - Once media is uploaded, it will be listed on the **Media** page, displaying metadata such as the title, artist, and file path.

3. **Play Media (WIP)**:
   - Click on any media item to play it within the browser.

---

## Future Enhancements

- Add support for playlists.
- Improve the media player interface.
- Add search functionality to filter media by title or artist.
- Portability.

## Licensing?

Feel free to modify the content based on your specific needs and updates!