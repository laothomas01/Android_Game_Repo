package com.GameVersion2.game.driver;

public class Skill {

    float projectileCount;
    float coolDownTimer;
    float MaxCoolDownTimer;
    boolean isOnCoolDown;
    String skillName;
    int level;

    float deltaRadian;
    float deltaPosition;


    public Skill() {
        deltaPosition = 0;
        deltaRadian = 0;
        projectileCount = 0f;
        MaxCoolDownTimer = 0f;
        coolDownTimer = MaxCoolDownTimer;
        isOnCoolDown = false;
        skillName = "";
        level = 1;
    }

    public Skill(String name, float MaxCoolDownTimer, boolean isOnCoolDown, float prjctileCount, int lvl, float angle, float pos) {
        this.setSkillName(name);
        this.setMaxCoolDownTimer(MaxCoolDownTimer);
        this.setCoolDown(MaxCoolDownTimer);
        this.setIsOnCoolDown(isOnCoolDown);
        this.setLevel(lvl);
        this.setProjectileCount(prjctileCount);
        this.setDeltaAngle(angle);
        this.setDeltaPosition(pos);
    }

    public String toString() {
        return "        SKILL NAME:     " + this.getSkillName() + "     COOLDOWN:       " + this.getCoolDown() + "      IS_ON_COOLDOWN      " + this.getIsOnCoolDown() + "      LEVEL       " + this.getLevel();
    }

    public void update(String name, float MaxCoolDownTimer, boolean isOnCoolDown, float projectileCount, int level) {
        this.setSkillName(name);
        this.setMaxCoolDownTimer(MaxCoolDownTimer);
        this.setCoolDown(MaxCoolDownTimer);
        this.setIsOnCoolDown(isOnCoolDown);
        this.setLevel(level);
        this.setProjectileCount(projectileCount);
    }

    public void update(float coolDownTimer, boolean isOnCoolDown) {
        this.setCoolDown(coolDownTimer);
        this.setIsOnCoolDown(isOnCoolDown);
    }

    public float getDeltaPosition() {
        return deltaPosition;
    }

    public float getDeltaAngle() {
        return deltaRadian;
    }

    public void setDeltaAngle(float rad) {
        this.deltaRadian = rad;
    }

    public void setDeltaPosition(float pos) {
        this.deltaPosition = pos;
    }


    public boolean hasFinishedCoolDown() {
        if (this.getIsOnCoolDown() && this.getCoolDown() <= 0) {
            return true;
        }
        return false;
    }


    public float getProjectileCount() {
        return this.projectileCount;
    }

    public void setProjectileCount(float count) {
        this.projectileCount = count;
    }

    public String getSkillName() {
        return this.skillName;
    }

    public void setSkillName(String name) {
        this.skillName = name;
    }

    public void setMaxCoolDownTimer(float max) {
        this.MaxCoolDownTimer = max;
    }

    public float getMaxCoolDownTimer() {
        return this.MaxCoolDownTimer;
    }

    public float getCoolDown() {
        return coolDownTimer;
    }

    public void setCoolDown(float coolDownTimer) {
        this.coolDownTimer = coolDownTimer;
    }

    public boolean getIsOnCoolDown() {
        return isOnCoolDown;
    }

    public void setIsOnCoolDown(boolean onCoolDown) {
        isOnCoolDown = onCoolDown;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


}
