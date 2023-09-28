module "pubsub" {
  source = "../pubsub-topic-subscription"
}

module "secrets" {
  source = "../secret-sa-binding"
}