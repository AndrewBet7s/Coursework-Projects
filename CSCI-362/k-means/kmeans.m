%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Naeem Tai
% CSCI 362
% kmeans.m
% 12/04/2013
%
% 		This program separated image pixels into k clusters based on their 
%       color values.
% 		The input image and the number of clusters are flexibe and are to 
%       be set by user.
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

function [] = kmeans(filename, k)
    
    % get input data
    k = k; % number of clusters
    image = imread(filename); % read an image


    gray = rgb2gray(image); % convert image to grayscale
    gray = im2double(gray);
    % create a copy of image that will be modified after final cluster 
    % assignments
    dynamic = gray;

    % declare matrix of n by m with all 0s
    [rows, cols] = size(gray);
    prev = zeros(rows, cols); % to store previous cluster assignment
    cur = ones(rows, cols); % to store current cluster assignment

    % Initialize k random centroids
    for i = 1:k
        row = randi(rows, 1);
        col = randi(cols, 1);
        centroids(i) = dynamic(row, col);
    end
    
    
    % assign each pixel to cluster c1 or c2 (1: cluster 1, 2: cluster 2)
    % reassign clusters while current and previous assignmnets are not same 
    % (reassign until pixels stop moving from one cluster to another)
    keepgoing = true;
    step = 0;
    while (keepgoing)
        prev = cur; % copy current assignment to previous

        % recalculate current cluster assignment
        for n=1:rows
            for m=1:cols
                % calculate distance from each centroid and find minimum distance
                mindist = 256;
                clust = 1;
                dist = 0;
                for i=1:k
                    dist = abs(centroids(i) - dynamic(n, m));
                    if (dist < mindist)
                        mindist = dist;
                        % assign a cluster number to a pixels's position
                        cur(n, m) = i;
                    end
                end
            end
        end

        % calculate new centroids
        for i=1:k
            temp = dynamic(find(cur == i));
            centroids(i) = mean(temp);
        end

        % check if the current cluster assignment is same as the previous
        if (isequal(prev, cur))
           keepgoing = false;
        else
            keepgoing = true;
        end

        step = step + 1;
        disp(['step: ' num2str(step)]);
        disp(isequal(prev, cur));
        disp(keepgoing);

    end

    % assign cluster values to pixels
    for n=1:rows
        for m=1:cols
          dynamic(n, m) = centroids(cur(n, m)); 
        end
    end

    % display original, grayscale, and cluster images
    figure, imshow(image);
    figure, imshow(gray);
    figure, imshow(dynamic);
    figure, imshow(mat2gray(cur));
end
