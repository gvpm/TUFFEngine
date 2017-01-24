/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuff.engine;

/**
 *
 * @author gvpm
 */
public class SimulationParameters {
    int speedLimit;
    float probP;
    int simulationTime;
    int discardTime;
    int statisticTime;
    float initialDensity;
    float deltaDendity;
    float finalDensity;
    int cellsInX;
    int cellsInY;
    float cellSize;
    int defaultCarSize;

    public SimulationParameters(int speedLimit, float probP, int simulationTime, int discardTime, int statisticTime, float initialDensity, float deltaDendity, float finalDensity, int cellsInX, int cellsInY, float cellSize, int defaultCarSize) {
        this.speedLimit = speedLimit;
        this.probP = probP;
        this.simulationTime = simulationTime;
        this.discardTime = discardTime;
        this.statisticTime = statisticTime;
        this.initialDensity = initialDensity;
        this.deltaDendity = deltaDendity;
        this.finalDensity = finalDensity;
        this.cellsInX = cellsInX;
        this.cellsInY = cellsInY;
        this.cellSize = cellSize;
        this.defaultCarSize = defaultCarSize;
    }

    public int getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(int speedLimit) {
        this.speedLimit = speedLimit;
    }

    public float getProbP() {
        return probP;
    }

    public void setProbP(float probP) {
        this.probP = probP;
    }

    public int getSimulationTime() {
        return simulationTime;
    }

    public void setSimulationTime(int simulationTime) {
        this.simulationTime = simulationTime;
    }

    public int getDiscardTime() {
        return discardTime;
    }

    public void setDiscardTime(int discardTime) {
        this.discardTime = discardTime;
    }

    public int getStatisticTime() {
        return statisticTime;
    }

    public void setStatisticTime(int statisticTime) {
        this.statisticTime = statisticTime;
    }

    public float getInitialDensity() {
        return initialDensity;
    }

    public void setInitialDensity(float initialDensity) {
        this.initialDensity = initialDensity;
    }

    public float getDeltaDendity() {
        return deltaDendity;
    }

    public void setDeltaDendity(float deltaDendity) {
        this.deltaDendity = deltaDendity;
    }

    public float getFinalDensity() {
        return finalDensity;
    }

    public void setFinalDensity(float finalDensity) {
        this.finalDensity = finalDensity;
    }

    public int getCellsInX() {
        return cellsInX;
    }

    public void setCellsInX(int cellsInX) {
        this.cellsInX = cellsInX;
    }

    public int getCellsInY() {
        return cellsInY;
    }

    public void setCellsInY(int cellsInY) {
        this.cellsInY = cellsInY;
    }

    public float getCellSize() {
        return cellSize;
    }

    public void setCellSize(float cellSize) {
        this.cellSize = cellSize;
    }

    public int getDefaultCarSize() {
        return defaultCarSize;
    }

    public void setDefaultCarSize(int defaultCarSize) {
        this.defaultCarSize = defaultCarSize;
    }
    
    
    
    
}
