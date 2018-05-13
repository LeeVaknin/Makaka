package server;

import data.Models.Puzzle;
import data.Models.PuzzleState;

import java.util.logging.Logger;

public class CacheManagerService implements ICacheManagerService {

    private Logger logger;
    private IRepositoryPuzzleService repositoryService;

    CacheManagerService(IRepositoryPuzzleService repositoryService) {
        this.logger = Logger.getLogger(CacheManagerService.class.getName());
        this.repositoryService = repositoryService;
    }

    @Override
    public boolean saveState(int id, Puzzle puzzle, PuzzleState puzzleState) {
        boolean isSaved = false;
        try {
            switch (puzzleState) {
                case INITIAL: {
                    isSaved = this.repositoryService.add(puzzle, PuzzleState.INITIAL);
                    break;
                }
                case CURRENT: {
                    if (id >= 0) {
                        isSaved = this.repositoryService.update(id, puzzle, PuzzleState.CURRENT);
                    } else {
                        isSaved = this.repositoryService.add(puzzle, PuzzleState.CURRENT);
                    }
                    break;
                }
                case SOLUTION: {
                    isSaved = this.repositoryService.add(puzzle, PuzzleState.SOLUTION);
                    break;
                }
            }
        } catch (Exception ex) {
            this.logger.warning("CacheManagerService.saveState(): Error details: " + ex.getMessage());
        }
        return isSaved;
    }

    @Override
    public Puzzle loadstate(int id, PuzzleState puzzleState) {
        return this.repositoryService.get(id, puzzleState);
    }

    @Override
    public boolean hasSolution(int id) {
        Puzzle puzzle = repositoryService.get(id, PuzzleState.SOLUTION);
        return (puzzle != null);

    }

    public boolean hasCurrent(int id) {
        Puzzle puzzle = this.repositoryService.get(id, PuzzleState.CURRENT);
        return (puzzle != null);
    }
}
