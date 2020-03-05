package projects.jaseem.triptimes.domain.extensions

import androidx.lifecycle.MutableLiveData
import projects.jaseem.triptimes.state.Resource
import projects.jaseem.triptimes.state.ResourceState


fun <T> MutableLiveData<Resource<T>>.setSuccess(data: T) =
    postValue(
        Resource(
            ResourceState.SUCCESS,
            data
        )
    )

fun <T> MutableLiveData<Resource<T>>.setLoading() =
    postValue(
        Resource(
            ResourceState.LOADING,
            value?.data
        )
    )

fun <T> MutableLiveData<Resource<T>>.setError(errorMessage: String? = null) =
    postValue(
        Resource(
            ResourceState.ERROR,
            value?.data,
            errorMessage
        )
    )