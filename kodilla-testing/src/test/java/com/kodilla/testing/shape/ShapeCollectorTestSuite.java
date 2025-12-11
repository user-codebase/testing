package com.kodilla.testing.shape;

import org.junit.jupiter.api.*;

public class ShapeCollectorTestSuite {

    private static int testCounter;

    @BeforeAll
    static void beforeAll(){
        System.out.println("Running Tests...");
        ShapeCollectorTestSuite.testCounter = 0;
    }

    @AfterAll
    static void afterAll(){
        System.out.println("All tests are finished");
    }


    @Nested
    @DisplayName("Running tests for ShapeCollector...")
    class TestShapeCollector{

        private ShapeCollector shapeCollector;

        @BeforeEach
        void beforeEach(){
            ShapeCollectorTestSuite.testCounter++;
            System.out.println("Unit Test number: " + ShapeCollectorTestSuite.testCounter + " has started...");
            this.shapeCollector = new ShapeCollector();
        }

        @AfterEach
        void afterEach(){
            System.out.println("Unit Test number: " + ShapeCollectorTestSuite.testCounter + " has been completed");
        }

        @Test
        public void testAddShape(){
            //Given
            Square square = new Square(5);

            //When
            this.shapeCollector.addFigure(square);
            String shapeInTheList = this.shapeCollector.showFigures();

            //Then
            Assertions.assertEquals("Square", shapeInTheList);
        }

        @Test
        public void getShape(){
            //Given
            Triangle triangle = new Triangle(2.5, 4.5);
            this.shapeCollector.addFigure(triangle);

            //When
            Shape returnShape = this.shapeCollector.getFigure(0);

            //Then
            Assertions.assertEquals(triangle, returnShape);
            Assertions.assertEquals("Triangle", this.shapeCollector.showFigures());
        }

        @Test
        public void removeNotExistingShape(){
            //Given
            Circle circle = new Circle(12);

            //When
            boolean isRemoved = this.shapeCollector.removeFigure(circle);
            this.shapeCollector.showFigures();

            //Then
            Assertions.assertFalse(isRemoved);
        }

        @Test
        public void removeExistingShape(){
            //Given
            Circle circle = new Circle(3);

            //When
            this.shapeCollector.addFigure(circle);
            boolean isRemoved = this.shapeCollector.removeFigure(circle);

            //Then
            Assertions.assertTrue(isRemoved);
            Assertions.assertEquals("", this.shapeCollector.showFigures());
        }


    }


}
