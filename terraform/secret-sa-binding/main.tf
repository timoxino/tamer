resource "google_secret_manager_secret_iam_binding" "secret_binding_tamer-openai-token" {
  secret_id = "projects/908519591128/secrets/tamer-openai-token"
  role      = "roles/secretmanager.secretAccessor"
  members   = ["serviceAccount:908519591128-compute@developer.gserviceaccount.com"]
}