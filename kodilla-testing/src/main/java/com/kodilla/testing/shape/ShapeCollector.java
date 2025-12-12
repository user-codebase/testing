package com.kodilla.testing.shape;

import java.util.ArrayList;
import java.util.List;

public class ShapeCollector {

    private List<Shape> listOfShapes;

    public ShapeCollector() {
        this.listOfShapes = new ArrayList<>();
    }

    public void addFigure(Shape shape){
        this.listOfShapes.add(shape);
    }

    public boolean removeFigure(Shape shape){
        if(this.listOfShapes.contains(shape)){
            this.listOfShapes.remove(shape);
            return true;
        }
        return false;
    }

    public Shape getFigure(int n){
        if(n<0 || n>this.listOfShapes.size()) return null;
        return  this.listOfShapes.get(n);
    }

    public String showFigures(){
        String result = "";
        for (Shape shape : listOfShapes) {
            result += shape.getShapeName();
        }
        return result;
    }

}
