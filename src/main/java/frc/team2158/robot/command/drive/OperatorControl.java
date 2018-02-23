package frc.team2158.robot.command.drive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.team2158.robot.OperatorInterface;
import frc.team2158.robot.Robot;
import frc.team2158.robot.RobotMap;
import frc.team2158.robot.subsystem.drive.DriveSubsystem;

import java.util.logging.Logger;

public class OperatorControl extends Command {
    private static final Logger LOGGER = Logger.getLogger(OperatorControl.class.getName());

    private DriveMode driveMode;
    private DriveSubsystem driveSubsystem;
    private Joystick joystick;

    public OperatorControl(DriveMode driveMode) {
        this.driveMode = driveMode;
        this.driveSubsystem = Robot.getDriveSubsystem();
        this.joystick = Robot.getOperatorInterface().getJoystick();
    }

    @Override
    public void execute() {
        switch(driveMode) {
            case TANK:
                driveSubsystem.tankDrive(joystick.getRawAxis(0), joystick.getRawAxis(1));
                break;
            case ARCADE:
                driveSubsystem.arcadeDrive(-joystick.getRawAxis(1), -joystick.getRawAxis(2));
                break;
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
