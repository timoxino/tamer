resource "google_pubsub_topic" "topic_pending_cv" {
  name = "pending_cv_topic"
}

resource "google_pubsub_subscription" "subscription_tamer" {
  name  = "tamer_subscription"
  topic = google_pubsub_topic.topic_pending_cv.name
  ack_deadline_seconds = 600
}
