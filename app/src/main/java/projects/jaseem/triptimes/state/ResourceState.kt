package projects.jaseem.triptimes.state

sealed class ResourceState {
    object SUCCESS : ResourceState()
    object ERROR : ResourceState()
    object LOADING : ResourceState()
}