# PM Insights

## Cursor Cloud specific instructions

### Architecture

- **Backend**: FastAPI (`app.py`) on port 8000, serving JSON API endpoints under `/api/` and legacy HTML pages at root.
- **Frontend**: React + Vite (`frontend/`) on port 5173, proxying `/api` requests to backend.
- **Database**: PostgreSQL with `psycopg` v3.
- **NLP Pipeline**: Faster Whisper for transcription, optional RuBERT classifier, sentiment analysis, Natasha NER.

### Running the application

1. Start PostgreSQL: `sudo pg_ctlcluster 16 main start`
2. Start backend: `cd /workspace && uvicorn app:app --host 127.0.0.1 --port 8000 --reload`
3. Start frontend: `cd /workspace/frontend && npx vite --host 0.0.0.0`

### Environment

- `.env` at repo root must contain `DATABASE_URL` and optionally `JWT_SECRET`, `WHISPER_MODEL_NAME` (use `tiny` for dev).
- Python deps in `requirements.txt`. Frontend deps managed by npm in `frontend/`.
- `ffmpeg` is a system dependency required by `pydub` for audio conversion.
- DejaVu fonts needed for Cyrillic PDF export (`fonts-dejavu-core` package).

### Key gotchas

- The Whisper model downloads on first startup (~100MB for `tiny`, ~3GB for `large-v3`). Use `WHISPER_MODEL_NAME=tiny` for faster dev startup.
- `uvicorn --reload` watches the whole `/workspace` directory; frontend file changes may trigger backend reload. This is harmless but creates log noise.
- The backend's `init_db()` runs on startup and handles schema migrations (adds `user_id` column if missing). No manual migration needed.
- All ML model imports (`torch`, `transformers`, `natasha`) are wrapped in try/except — the app works in "rules-only" mode if they fail to import.
- The Vite dev server proxies `/api` to the backend (configured in `vite.config.js`).

### Linting

- Python: `ruff check *.py` or `flake8 --max-line-length=120 *.py`
- Frontend: Standard ESLint via `cd frontend && npx eslint src/`

### Testing

- No automated test suite exists yet. Test manually via the UI or curl against `/api/` endpoints.
- Auth flow: `POST /api/auth/register`, `POST /api/auth/login`, `GET /api/auth/me` (requires `Authorization: Bearer <token>` header).
- Upload: `POST /api/upload` with multipart form data and auth header.
- Records: `GET /api/records`, `GET /api/records/{id}`, `DELETE /api/records/{id}` — all scoped to authenticated user.
