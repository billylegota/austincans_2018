package frc.team2158.robot.command.intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.team2158.robot.Robot;
import frc.team2158.robot.subsystem.intake.IntakeSubsystem;

public class Outtake extends Command {
    public Outtake() {
        requires(Robot.getIntakeSubsystem());
    }

    @Override
    protected void initialize() {
        Robot.getIntakeSubsystem().runIntake(IntakeSubsystem.IntakeDirection.OUT);
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}
