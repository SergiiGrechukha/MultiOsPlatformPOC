//
//  TableViewController.h
//  ios
//
//  Created by Sergiy on 2/11/18.
//  Copyright © 2018 loki. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface TableViewController : UIViewController
@property (weak, nonatomic) IBOutlet UILabel *myLabel;
@property (weak, nonatomic) IBOutlet UICollectionView *collectionView;

@end
