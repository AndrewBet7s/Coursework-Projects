%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Naeem Tai
% CSCI 362
% simple_kmeans.m
% 12/04/2013
%
% 		This program runs the kmeans function to separate image pixels of a
% 		specified image into specified k clusters.
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


close all % closes all of the figures
clear all % deletes all stored variables in workspace
clc % clear command window

% run kmeans
kmeans('soccerball.bmp', 3);