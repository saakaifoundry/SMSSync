/*
 * Copyright (c) 2010 - 2015 Ushahidi Inc
 * All rights reserved
 * Contact: team@ushahidi.com
 * Website: http://www.ushahidi.com
 * GNU Lesser General Public License Usage
 * This file may be used under the terms of the GNU Lesser
 * General Public License version 3 as published by the Free Software
 * Foundation and appearing in the file LICENSE.LGPL included in the
 * packaging of this file. Please review the following information to
 * ensure the GNU Lesser General Public License version 3 requirements
 * will be met: http://www.gnu.org/licenses/lgpl.html.
 *
 * If you have questions regarding the use of this file, please contact
 * Ushahidi developers at team@ushahidi.com.
 */

package org.addhen.smssync.domain.usecase.filter;

import com.addhen.android.raiburari.domain.executor.PostExecutionThread;
import com.addhen.android.raiburari.domain.executor.ThreadExecutor;
import com.addhen.android.raiburari.domain.usecase.Usecase;

import org.addhen.smssync.domain.entity.FilterEntity;
import org.addhen.smssync.domain.repository.FilterRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * @author Ushahidi Team <team@ushahidi.com>
 */
public class AddFilterUsecase extends Usecase {

    private final FilterRepository mFilterRepository;

    private FilterEntity mFilter;

    @Inject
    protected AddFilterUsecase(FilterRepository filterRepository, ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        mFilterRepository = filterRepository;
    }

    public void setFilter(FilterEntity filter) {
        mFilter = filter;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        if (mFilter == null) {
            throw new RuntimeException("FilterEntity is null. You must call setFilter(...)");
        }
        return mFilterRepository.addEntity(mFilter);
    }
}
