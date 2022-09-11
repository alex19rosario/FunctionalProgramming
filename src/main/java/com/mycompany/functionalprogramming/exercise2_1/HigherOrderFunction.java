/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.functionalprogramming.exercise2_1;

/**
 *
 * @author carlos
 */
public interface HigherOrderFunction <T, U, V> {
    Function<Function<U, V>, Function<Function<T, U>, Function<T, V>>> compose();
}
