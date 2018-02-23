package frc.team2158.robot.command.intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2158.robot.Robot;
import frc.team2158.robot.subsystem.intake.IntakeSubsystem;

public class Intake extends Command {
    public Intake() {
        requires(Robot.getIntakeSubsystem());
    }

    @Override
    protected void initialize() {
        Robot.getIntakeSubsystem().runIntake(IntakeSubsystem.IntakeDirection.IN);
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}
