terraform {
  backend "s3" {
    bucket         = "product-service548065"
    key            = "terraform.tfstate"
    region         = "eu-north-1"
    dynamodb_table = "tf-lock"
  }
}
