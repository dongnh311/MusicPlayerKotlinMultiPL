//
//  MusicObserver.m
//  iosApp
//
//  Created by Nguyen Hoai Dong on 22/4/24.
//  Copyright © 2024 orgName. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@protocol MusicObserver
@required
- (void)observeValueForKeyPath:(NSString *)keyPath
                      ofObject:(id)object
                        change:(NSDictionary<NSKeyValueChangeKey, id> *)change
                       context:(void *)context;
@end;

NS_ASSUME_NONNULL_END