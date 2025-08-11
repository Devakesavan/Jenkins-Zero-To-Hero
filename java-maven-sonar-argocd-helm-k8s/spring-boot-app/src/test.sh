#!/bin/bash
set -e

echo "🧪 Running tests..."

# Navigate to the project root (adjust path if needed)
cd "$(dirname "$0")"

# Run tests
mvn test

echo "✅ All tests passed successfully!"
