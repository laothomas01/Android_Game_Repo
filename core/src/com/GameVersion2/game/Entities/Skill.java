package com.GameVersion2.game.Entities;

/**
 * Correction: not an Entity
 * an entity HAS a skill
 */
public class Skill {

    float projectileCount;
    float coolDownTimer;
    float maxCoolDownTime;
    boolean isOnCoolDown;
    String skillName;
    String description;
    int level;

    float deltaRadian;
    float deltaPosition;


    public Skill() {
        deltaPosition = 0;
        deltaRadian = 0;
        projectileCount = 0f;
        maxCoolDownTime = 0f;
        coolDownTimer = maxCoolDownTime;
        isOnCoolDown = false;
        skillName = "";
        description = "";
        level = 1;
    }

    public Skill(String name, String descript, float maxCoolDownTimer, boolean isOnCoolDown, float prjctileCount, int lvl, float angle, float pos) {
        this.setSkillName(name);
        this.setmaxCoolDownTimer(maxCoolDownTimer);
        this.setCoolDown(maxCoolDownTimer);
        this.setIsOnCoolDown(isOnCoolDown);
        this.setLevel(lvl);
        this.setProjectileCount(prjctileCount);
        this.setDeltaAngle(angle);
        this.setDeltaPosition(pos);
        this.description = descript;
    }

    public String toString() {
        return "        SKILL NAME:     " + this.getSkillName() + "     COOLDOWN:       " + this.getCoolDownTimer() + "      IS_ON_COOLDOWN      " + this.getIsOnCoolDown() + "      LEVEL       " + this.getLevel();
    }

    public void update(String name, float maxCoolDownTimer, boolean isOnCoolDown, float projectileCount, int level) {
        this.setSkillName(name);
        this.setmaxCoolDownTimer(maxCoolDownTimer);
        this.setCoolDown(maxCoolDownTimer);
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
        if (this.getIsOnCoolDown() && this.getCoolDownTimer() > this.getmaxCoolDownTime()) {
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

    public void setmaxCoolDownTimer(float max) {
        this.maxCoolDownTime = max;
    }

    public float getmaxCoolDownTime() {
        return this.maxCoolDownTime;
    }

    public float getCoolDownTimer() {
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
