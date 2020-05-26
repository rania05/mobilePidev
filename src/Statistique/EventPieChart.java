/**
 * Copyright (C) 2009 - 2013 SC 4ViewSoft SRL
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package Statistique;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.models.SeriesSelection;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.Form;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.geom.Shape;
import entities.Refugie;
import java.util.ArrayList;
import services.RefugieService;
import java.util.Random;





/**
 * Budget demo pie chart.
 */
public class EventPieChart extends AbstractDemoChart {
      ArrayList<Refugie> list=RefugieService.getInstance().nombreRefugie();

    /**
   * Returns the chart name.
   * 
   * @return the chart name
   */
  public String getName() {
      
    return "Refugie chart";
  }
  
    public int getRandomColor(){
   Random rnd = new Random();
   return ColorUtil.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
}
    
@Override
    protected CategorySeries buildCategoryDataset(String title, double[] values) {
    CategorySeries series = new CategorySeries(title);
    int k = 0;
    double v;
     System.out.println("hetha houa "+list.size());

    for (Refugie c : RefugieService.getInstance().nombreRefugie()) {
       
      series.add( c.getNomCamp(),c.getNbrR());
      
    
    }
    return series;
  }
  /**
   * Returns the chart description.
   * 
   * @return the chart description
   */
  public String getDesc() {
    return "The number of refugie available per camp";
  }

  /**
   * Executes the chart demo.
   * 
   * @param context the context
   * @return the built intent
   */
  public Form execute() {
      double[] values = new double[30];
      int[] colors = new int[30]; 
        Integer i=0;
        for (Refugie c : RefugieService.getInstance().nombreRefugie()) {
            values[i] = (double)c.getNbrR();
            i++;     
        }
        Integer j=0;
        for (Refugie c : RefugieService.getInstance().nombreRefugie()) {
            colors[j] = getRandomColor();
            j++;     
        }
        
    final DefaultRenderer renderer = buildCategoryRenderer(colors);
    renderer.setZoomButtonsVisible(true);
    renderer.setZoomEnabled(true);
    renderer.setChartTitleTextFont(largeFont);
    renderer.setDisplayValues(true);
    renderer.setShowLabels(true);
    renderer.setBackgroundColor(ColorUtil.rgb(243, 242, 242));
    renderer.setApplyBackgroundColor(true);
    renderer.setLabelsColor(0000);
    final CategorySeries seriesSet = buildCategoryDataset("Project Refugié", values);
    final PieChart chart = new PieChart(seriesSet, renderer);
    ChartComponent comp = new ChartComponent(chart);
    return wrap("Statistique Refugié", comp);
    

}}