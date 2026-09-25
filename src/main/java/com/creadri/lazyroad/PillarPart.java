package com.creadri.lazyroad;

public class PillarPart {
    private String[][] baseBlockDatas;
    private int baseHeight;
        private int repeatEvery;
    private String[][] blockDatas;
    private int height;
    private int width;
    private int buildUntil;

        public String[][] getBaseBlockDatas() {
        return baseBlockDatas;
    }

    public void setBaseBlockDatas(String[][] baseBlockDatas) {
        this.baseBlockDatas = baseBlockDatas;
    }

    public int getBaseHeight() {
        return baseHeight;
    }

    public void setBaseHeight(int baseHeight) {
        this.baseHeight = baseHeight;
    }

        public int getRepeatEvery() {
        return repeatEvery;
    }
    public void setRepeatEvery(int repeatEvery) {
        this.repeatEvery = repeatEvery;
    }
    public PillarPart() {}

    public PillarPart(int height, int width) {
        blockDatas = new String[height][width];
        this.height = height;
        this.width = width;
    }

    public String[][] getBlockDatas() {
        return blockDatas;
    }

    public void setBlockDatas(String[][] blockDatas) {
        this.blockDatas = blockDatas;
    }

    public int getBuildUntil() {
        return buildUntil;
    }

    public void setBuildUntil(int buildUntil) {
        this.buildUntil = buildUntil;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}