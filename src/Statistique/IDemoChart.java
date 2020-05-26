/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statistique;

import com.codename1.ui.Form;


/**
 *
 * @author ASUS
 */


public interface IDemoChart {
  /** A constant for the name field in a list activity. */
  String NAME = "name";
  /** A constant for the description field in a list activity. */
  String DESC = "desc";

  /**
   * Returns the chart name.
   * 
   * @return the chart name
   */
  String getName();

  /**
   * Returns the chart description.
   * 
   * @return the chart description
   */
  String getDesc();

  /**
   * Executes the chart demo.
   * 
   * @param context the context
   * @return the built intent
   */
  Form execute();

}
