package Practical5.Prac5_2;

abstract class Employee
{
    String name;
    int id;

    Employee(String name, int id)
    {
        this.name = name;
        this.id = id;
    }

    abstract double monthlySalary();
}

class FullTime extends Employee
{
    double fixedSalary;

    FullTime(String name, int id, double fixedSalary)
    {
        super(name, id);
        this.fixedSalary = fixedSalary;
    }

    @Override
    double monthlySalary()
    {
        return fixedSalary;
    }
}

class PartTime extends Employee
{
    double hours;
    double rate;

    PartTime(String name, int id, double hours, double rate)
    {
        super(name, id);
        this.hours = hours;
        this.rate = rate;
    }

    @Override
    double monthlySalary()
    {
        return hours * rate;
    }
}

class Intern extends Employee
{
    double stipend;

    Intern(String name, int id, double stipend)
    {
        super(name, id);
        this.stipend = stipend;
    }

    @Override
    double monthlySalary()
    {
        return stipend;
    }
}

public class Payroll
{
    public static void main(String[] args)
    {
        Employee[] employees = {
            new FullTime("Rahul", 101, 50000),
            new PartTime("Priya", 102, 80, 300),
            new Intern("Amit", 103, 15000),
            new FullTime("Neha", 104, 60000),
            new PartTime("Riya", 105, 60, 250)
        };

        double total = 0;

        for (Employee emp : employees)
        {
            double salary = emp.monthlySalary();

            System.out.println(
                "Name: " + emp.name +
                ", ID: " + emp.id +
                ", Salary: ₹" + salary
            );

            total = total + salary;

            if (emp instanceof Intern)
            {
                System.out.println("Note: This employee is an Intern.");
            }
        }

        System.out.println("-------------------------");
        System.out.println("Total Payroll: ₹" + total);
    }
}
