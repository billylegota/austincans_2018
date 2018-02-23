package frc.team2158.robot.command.intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2158.robot.Robot;
import frc.team2158.robot.subsystem.intake.IntakeSubsystem;

public class Clockwise extends Command {
    public Clockwise() {
        requires(Robot.getIntakeSubsystem());
    }

    @Override
    protected void initialize() {
        Robot.getIntakeSubsystem().runIntake(IntakeSubsystem.IntakeDirection.CLOCKWISE);
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}
