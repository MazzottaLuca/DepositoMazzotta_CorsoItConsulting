#!/bin/bash
# GESTIONALE PIZZERIA - QUICK SETUP SCRIPT
# Uso: bash setup.sh

echo "🍕 GESTIONALE PIZZERIA - Auto Setup"
echo "=================================="
echo ""

# Check prerequisites
echo "📋 Checking prerequisites..."

# Check Java
if ! command -v java &> /dev/null; then
    echo "❌ Java not found. Please install Java 17+"
    exit 1
fi
echo "✅ Java installed: $(java -version 2>&1 | head -1)"

# Check Maven
if ! command -v mvn &> /dev/null; then
    echo "❌ Maven not found. Please install Maven 3.6+"
    exit 1
fi
echo "✅ Maven installed: $(mvn -v | head -1)"

# Check MySQL
if ! command -v mysql &> /dev/null; then
    echo "⚠️ MySQL client not found (optional, but server should be running)"
else
    echo "✅ MySQL client installed"
fi

echo ""
echo "🔧 Building Spring Boot application..."
mvn clean install -DskipTests

if [ $? -ne 0 ]; then
    echo "❌ Build failed!"
    exit 1
fi

echo ""
echo "✅ Build successful!"
echo ""
echo "🚀 Start the application with:"
echo "   mvn spring-boot:run"
echo ""
echo "📱 Then open in browser:"
echo "   http://localhost:8080"
echo ""
echo "🧪 Test the API with:"
echo "   bash test-api.sh"
echo ""
echo "📚 Documentation available in:"
echo "   - README.md"
echo "   - QUICK_START.md"
echo "   - API_DOCUMENTATION.md"
echo "   - PROJECT_SUMMARY.md"
echo ""
echo "=================================="
echo "Setup completed! Ready to go! 🎉"
echo "=================================="
