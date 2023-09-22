resource "google_cloud_run_service_iam_binding" "service_binding_tamer" {
  location = "us-central1"
  service  = "tamer"
  role     = "roles/run.invoker"
  members  = ["serviceAccount:${google_service_account.sa_cloud_run.email}"]
}