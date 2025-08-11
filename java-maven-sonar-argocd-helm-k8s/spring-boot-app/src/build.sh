#!/bin/bash
set -e

echo "🚀 Starting build process..."

# Navigate to the project root (adjust path if needed)
cd "$(dirname "$0")"

# Clean and package the Spring Boot app without running tests
mvn clean package -DskipTests

echo "✅ Build completed successfully!"
