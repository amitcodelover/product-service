variable "aws_region" {
  description = "The AWS region to deploy resources in."
  type        = string
  default     = "eu-north-1"
}

variable "ecr_repository_name" {
  description = "The name of the ECR repository."
  type        = string
  default     = "product/product-service"
}

variable "ecs_cluster_name" {
  description = "The name of the ECS cluster."
  type        = string
  default     = "product-service"
}

variable "ecs_service_name" {
  description = "The name of the ECS service."
  type        = string
  default     = "product-service-service-4g35gdxv"
}

variable "ecs_task_family" {
  description = "The family of the ECS task definition."
  type        = string
  default     = "product-service"
}

variable "app_port" {
  description = "The port the application container listens on."
  type        = number
  default     = 8080
}

variable "app_image" {
  description = "The Docker image for the application."
  type        = string
}
