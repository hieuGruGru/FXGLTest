package com.example.fxgltest;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;
import static com.almasb.fxgl.dsl.FXGL.getAppHeight;
import static com.almasb.fxgl.dsl.FXGL.getAppWidth;
import static com.almasb.fxgl.dsl.FXGL.getInput;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameWorld;

import com.almasb.fxgl.dsl.components.OffscreenCleanComponent;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.dsl.components.RandomMoveComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class GeoWarsFactory implements EntityFactory {

    public void setPlayer(Entity player) {
    }

    @Spawns("player")
    public Entity newPlayer(SpawnData data) {
        return entityBuilder()
                .from(data)
                .type(EntityType.PLAYER)
                .viewWithBBox(new Circle(20, Color.BLACK))
                .collidable()
                .build();
    }

    @Spawns("bullet")
    public Entity newBullet(SpawnData data) {
        Entity player = getGameWorld().getSingleton(EntityType.PLAYER);
        Point2D direction = getInput().getMousePositionWorld()
                .subtract(player.getCenter());

        return entityBuilder()
                .from(data)
                .type(EntityType.BULLET)
                .viewWithBBox(new Rectangle(10, 2, Color.BLACK))
                .collidable()
                .with(new ProjectileComponent(direction, 2000))
                .with(new OffscreenCleanComponent())
                .build();
    }

    @Spawns("enemy")
    public Entity newEnemy(SpawnData data) {
        return entityBuilder()
                .from(data)
                .type(EntityType.ENEMY)
                .viewWithBBox("laclaclaclac.png")
                .collidable()
                .with(new RandomMoveComponent(
                        new Rectangle2D(0, 0,
                                getAppWidth(), getAppHeight()), 50))
                .build();
    }
}