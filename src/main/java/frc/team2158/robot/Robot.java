package frc.team2158.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.team2158.robot.command.drive.DriveMode;
import frc.team2158.robot.command.drive.OperatorControl;
import frc.team2158.robot.command.drive.ToggleGearMode;
import frc.team2158.robot.command.intake.*;
import frc.team2158.robot.command.lift.MoveLift;
import frc.team2158.robot.subsystem.drive.DriveSubsystem;
import frc.team2158.robot.subsystem.drive.TalonSRXGroup;
import frc.team2158.robot.subsystem.intake.IntakeSubsystem;
import frc.team2158.robot.subsystem.lift.LiftSubsystem;

import java.util.logging.Logger;

public class Robot extends TimedRobot {
    private static final Logger LOGGER = Logger.getLogger(Robot.class.getName());
    private static final LoggingSystem LOGGING_SYSTEM = LoggingSystem.getInstance();

    private static DriveSubsystem driveSubsystem;
    private static LiftSubsystem liftSubsystem;
    private static IntakeSubsystem intakeSubsystem;

    private static OperatorInterface operatorInterface;

    @Override
    public void robotInit() {
        // Initialize the drive subsystem.
        driveSubsystem = new DriveSubsystem(
                new TalonSRXGroup(
                        new WPI_TalonSRX(RobotMap.LEFT_MOTOR_1), // This motor is the master for the left side.
                        new WPI_TalonSRX(RobotMap.LEFT_MOTOR_2),
                        new WPI_TalonSRX(RobotMap.LEFT_MOTOR_3)
                ),
                new TalonSRXGroup(
                        new WPI_TalonSRX(RobotMap.RIGHT_MOTOR_1), // This motor is the master for the right side.
                        new WPI_TalonSRX(RobotMap.RIGHT_MOTOR_2),
                        new WPI_TalonSRX(RobotMap.RIGHT_MOTOR_3)
                ),
                new DoubleSolenoid(RobotMap.PCM_ADDRESS, RobotMap.GEARBOX_FORWARD_CHANNEL,
                        RobotMap.GEARBOX_REVERSE_CHANNEL)
        );

        // Initialize the lift subsystem.
        liftSubsystem = new LiftSubsystem(
                new SpeedControllerGroup(
                        new Spark(RobotMap.LIFT_MOTOR_1),
                        new Spark(RobotMap.LIFT_MOTOR_2),
                        new Spark(RobotMap.LIFT_MOTOR_3)
                )
        );

        // Initialize the intake subsystem.
        intakeSubsystem = new IntakeSubsystem(
                new Spark(RobotMap.LEFT_INTAKE_MOTOR),
                new Spark(RobotMap.RIGHT_INTAKE_MOTOR),
                new Spark(RobotMap.PIVOT_INTAKE_MOTOR),
                new DoubleSolenoid(RobotMap.PCM_ADDRESS, RobotMap.INTAKE_SOLENOID_1, RobotMap.INTAKE_SOLENOID_2)
        );

        // Initialize the operator interface.
        operatorInterface = new OperatorInterface();

        LOGGER.info("Robot initialization completed.");
    }

    public static DriveSubsystem getDriveSubsystem() {
        if(driveSubsystem != null) {
            return driveSubsystem;
        }
        throw new RuntimeException("Drive subsystem has not yet been initialized!");
    }

    public static LiftSubsystem getLiftSubsystem() {
        if(liftSubsystem != null) {
            return liftSubsystem;
        }
        throw new RuntimeException("Lift subsystem has not yet been initialized!");
    }

    public static IntakeSubsystem getIntakeSubsystem() {
        if(intakeSubsystem != null) {
            return intakeSubsystem;
        }
        throw new RuntimeException("Intake subsystem has not yet been initialized!");
    }

    public static OperatorInterface getOperatorInterface() {
        return operatorInterface;
    }

    @Override
    public void teleopInit() {
        operatorInterface.bindButton("buttonLB", OperatorInterface.ButtonMode.WHILE_HELD, new Intake());
        operatorInterface.bindButton("buttonLT", OperatorInterface.ButtonMode.WHILE_HELD, new Outtake());
        operatorInterface.bindButton("buttonY", OperatorInterface.ButtonMode.WHEN_PRESSED, new ToggleIntakeSolenoid());
        operatorInterface.bindButton("buttonRB", OperatorInterface.ButtonMode.WHILE_HELD, new MoveLift(LiftSubsystem.Direction.UP, -1));
        operatorInterface.bindButton("buttonRT", OperatorInterface.ButtonMode.WHILE_HELD, new MoveLift(LiftSubsystem.Direction.DOWN, -0.5));
        operatorInterface.bindButton("buttonX", OperatorInterface.ButtonMode.WHILE_HELD, new CounterClockwise());
        operatorInterface.bindButton("buttonB", OperatorInterface.ButtonMode.WHILE_HELD, new Clockwise());
        Scheduler.getInstance().add(new OperatorControl(DriveMode.ARCADE));
        // TODO: Make lift inverted.
        // TODO: Invert the intake direction correctly.
    }

    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
}
