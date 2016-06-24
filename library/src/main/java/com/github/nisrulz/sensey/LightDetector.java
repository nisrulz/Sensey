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

public class LightDetector {

  private final LightListener lightListener;
  private final float threshold;
  final SensorEventListener sensorEventListener = new SensorEventListener() {
    @Override public void onSensorChanged(SensorEvent sensorEvent) {
      if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT) {
        float lux = sensorEvent.values[0];
        if (lux < threshold) {
          // Dark
          lightListener.onDark();
        } else {
          // Not Dark
          lightListener.onLight();
        }
      }
    }

    @Override public void onAccuracyChanged(Sensor sensor, int i) {
      // do nothing
    }
  };

  public LightDetector(LightListener lightListener) {
    threshold = 3.0f;
    this.lightListener = lightListener;
  }

  public LightDetector(float threshold, LightListener lightListener) {
    this.threshold = threshold;
    this.lightListener = lightListener;
  }

  public interface LightListener {
    void onDark();

    void onLight();
  }
}
