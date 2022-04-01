package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;

public class IndexOut extends CommandBase {
    private final Indexer indexer;
    private final double speed;
    public IndexOut(Indexer subsystem, double speed) {
        indexer = subsystem;
        this.speed = speed;
        addRequirements(indexer);
    }
    @Override
    public void execute() {
        indexer.indexOut(speed);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        indexer.halt();
    }
}
