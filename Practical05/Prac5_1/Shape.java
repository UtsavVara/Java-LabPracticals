package Practical5.Prac5_1;

abstract class Shape
{
    abstract double area();
}

class Circle extends Shape
{
    double radius;

    Circle(double radius)
    {
        this.radius = radius;
    }

    @Override
    double area()
    {
        return Math.PI * radius * radius;
    }
}

class Rectangle extends Shape
{
    double length;
    double width;

    Rectangle(double length, double width)
    {
        this.length = length;
        this.width = width;
    }

    @Override
    double area()
    {
        return length * width;
    }
}

class Triangle extends Shape
{
    double base;
    double height;

    Triangle(double base, double height)
    {
        this.base = base;
        this.height = height;
    }

    @Override
    double area()
    {
        return 0.5 * base * height;
    }
}

class Main
{
    public static void main(String[] args)
    {
        Shape[] shapes = {
            new Circle(5),
            new Rectangle(4, 6),
            new Triangle(8, 3),
            new Circle(2)
        };

        double total = 0;
        double largest = 0;

        for (Shape s : shapes)
        {
            double currentArea = s.area();

            System.out.println("Area = " + currentArea);

            total = total + currentArea;

            if (currentArea > largest)
            {
                largest = currentArea;
            }

            System.out.println("Running total = " + total);
        }

        System.out.println("\nFinal total = " + total);
        System.out.println("Largest area = " + largest);
    }
}
