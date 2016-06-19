/*
 * Copyright (C) 2016 Nishant Srivastava
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.nisrulz.sensey;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

public class FlipDetector {

  private final FlipListener flipListener;
  final SensorEventListener sensorEventListener = new SensorEventListener() {
    @Override public void onSensorChanged(SensorEvent sensorEvent) {
      if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
        float z = sensorEvent.values[2];
        if (z > 9 && z < 10) {
          flipListener.onFaceUp();
        } else if (z > -10 && z < -9) {
          flipListener.onFaceDown();
        }
      }
    }

    @Override public void onAccuracyChanged(Sensor sensor, int i) {
      // do nothing
    }
  };

  public FlipDetector(FlipListener flipListener) {
    this.flipListener = flipListener;
  }

  public interface FlipListener {
    void onFaceUp();

    void onFaceDown();
  }
}
